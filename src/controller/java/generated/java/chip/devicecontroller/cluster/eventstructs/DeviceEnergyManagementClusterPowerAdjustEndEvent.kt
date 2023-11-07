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

class DeviceEnergyManagementClusterPowerAdjustEndEvent(
  val cause: Int,
  val duration: Long,
  val energyUse: Long
) {
  override fun toString(): String = buildString {
    append("DeviceEnergyManagementClusterPowerAdjustEndEvent {\n")
    append("\tcause : $cause\n")
    append("\tduration : $duration\n")
    append("\tenergyUse : $energyUse\n")
    append("}\n")
  }

  fun toTlv(tag: Tag, tlvWriter: TlvWriter) {
    tlvWriter.apply {
      startStructure(tag)
      put(ContextSpecificTag(TAG_CAUSE), cause)
      put(ContextSpecificTag(TAG_DURATION), duration)
      put(ContextSpecificTag(TAG_ENERGY_USE), energyUse)
      endStructure()
    }
  }

  companion object {
    private const val TAG_CAUSE = 0
    private const val TAG_DURATION = 1
    private const val TAG_ENERGY_USE = 2

    fun fromTlv(tag: Tag, tlvReader: TlvReader): DeviceEnergyManagementClusterPowerAdjustEndEvent {
      tlvReader.enterStructure(tag)
      val cause = tlvReader.getInt(ContextSpecificTag(TAG_CAUSE))
      val duration = tlvReader.getLong(ContextSpecificTag(TAG_DURATION))
      val energyUse = tlvReader.getLong(ContextSpecificTag(TAG_ENERGY_USE))

      tlvReader.exitContainer()

      return DeviceEnergyManagementClusterPowerAdjustEndEvent(cause, duration, energyUse)
    }
  }
}
