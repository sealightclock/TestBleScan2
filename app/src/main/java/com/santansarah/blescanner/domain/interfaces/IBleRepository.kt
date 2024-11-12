package com.santansarah.blescanner.domain.interfaces

import android.os.ParcelUuid
import com.santansarah.blescanner.data.local.entities.BleCharacteristic
import com.santansarah.blescanner.data.local.entities.Company
import com.santansarah.blescanner.data.local.entities.Descriptor
import com.santansarah.blescanner.data.local.entities.MicrosoftDevice
import com.santansarah.blescanner.data.local.entities.ScannedDevice
import com.santansarah.blescanner.data.local.entities.Service
import com.santansarah.blescanner.domain.models.ScanFilterOption
import kotlinx.coroutines.flow.Flow

interface IBleRepository {

    suspend fun getCompanyById(id: Int): Company?

    suspend fun getServiceById(uuid: String): Service?

    suspend fun getCharacteristicById(uuid: String): BleCharacteristic?

    suspend fun getMicrosoftDeviceById(id: Int): MicrosoftDevice?

    suspend fun insertDevice(device: ScannedDevice): Long

    suspend fun getDeviceByAddress(address: String): ScannedDevice?

    suspend fun deleteScans()

    fun getScannedDevices(scanFilter: ScanFilterOption?): Flow<List<ScannedDevice>>

    suspend fun getMsDevice(
        byteArray: ByteArray
    ): String?

    suspend fun getServices(
        serviceIdRecord: List<ParcelUuid>
    ): List<String>?

    suspend fun getDescriptorById(uuid: String): Descriptor?

    suspend fun updateDevice(scannedDevice: ScannedDevice)

    suspend fun deleteNotSeen()

}
