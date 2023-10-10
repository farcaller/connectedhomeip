/*
 *
 *    Copyright (c) 2023 Project CHIP Authors
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package chip.devicecontroller.cluster.eventstructs

import chip.devicecontroller.cluster.*
import chip.tlv.ContextSpecificTag
import chip.tlv.Tag
import chip.tlv.TlvReader
import chip.tlv.TlvWriter

class ElectricalEnergyMeasurementClusterEphemeralEnergyExportedEvent(
  val exportedTimestamp: Long,
  val energyExported: Long
) {
  override fun toString(): String = buildString {
    append("ElectricalEnergyMeasurementClusterEphemeralEnergyExportedEvent {\n")
    append("\texportedTimestamp : $exportedTimestamp\n")
    append("\tenergyExported : $energyExported\n")
    append("}\n")
  }

  fun toTlv(tag: Tag, tlvWriter: TlvWriter) {
    tlvWriter.apply {
      startStructure(tag)
      put(ContextSpecificTag(TAG_EXPORTED_TIMESTAMP), exportedTimestamp)
      put(ContextSpecificTag(TAG_ENERGY_EXPORTED), energyExported)
      endStructure()
    }
  }

  companion object {
    private const val TAG_EXPORTED_TIMESTAMP = 0
    private const val TAG_ENERGY_EXPORTED = 1

    fun fromTlv(
      tag: Tag,
      tlvReader: TlvReader
    ): ElectricalEnergyMeasurementClusterEphemeralEnergyExportedEvent {
      tlvReader.enterStructure(tag)
      val exportedTimestamp = tlvReader.getLong(ContextSpecificTag(TAG_EXPORTED_TIMESTAMP))
      val energyExported = tlvReader.getLong(ContextSpecificTag(TAG_ENERGY_EXPORTED))

      tlvReader.exitContainer()

      return ElectricalEnergyMeasurementClusterEphemeralEnergyExportedEvent(
        exportedTimestamp,
        energyExported
      )
    }
  }
}
