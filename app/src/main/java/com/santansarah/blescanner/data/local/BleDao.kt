package com.santansarah.blescanner.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.santansarah.blescanner.data.local.entities.BleCharacteristic
import com.santansarah.blescanner.data.local.entities.Company
import com.santansarah.blescanner.data.local.entities.Descriptor
import com.santansarah.blescanner.data.local.entities.MicrosoftDevice
import com.santansarah.blescanner.data.local.entities.ScannedDevice
import com.santansarah.blescanner.data.local.entities.Service
import kotlinx.coroutines.flow.Flow

@Dao
interface BleDao {

    @Query("SELECT * FROM companies WHERE code = :companyId")
    fun getCompanyById(companyId: Int): Company?

    @Query("SELECT * FROM services where uuid = :uuid")
   fun getServiceByUuid(uuid: String): Service?

    @Query("SELECT * FROM characteristics where uuid = :uuid")
   fun getCharacteristicsByUuid(uuid: String): BleCharacteristic?

    @Query("SELECT * FROM MicrosoftDevices where id = :id")
   fun getMicrosoftDevice(id: Int): MicrosoftDevice?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun insertDevice(device: ScannedDevice): Long

    @Query("SELECT * from scanned")
    fun getScannedDevices(): Flow<List<ScannedDevice>>

    @Query("SELECT * from scanned where address = :address")
   fun getDeviceByAddress(address: String): ScannedDevice?

    @Query("DELETE from scanned")
   fun deleteScans()

    @Query("SELECT * FROM descriptors where uuid = :uuid")
   fun getDescriptorByUuid(uuid: String): Descriptor?

    @Update
   fun updateDevice(scannedDevice: ScannedDevice)

    @Query(
        """delete from scanned where 
        (julianday(CURRENT_TIMESTAMP)-julianday(datetime(lastSeen/1000, 'unixepoch')))*86400000 > 15000 
        and customName is null and favorite = 0""")
   fun deleteNotSeen()
}

