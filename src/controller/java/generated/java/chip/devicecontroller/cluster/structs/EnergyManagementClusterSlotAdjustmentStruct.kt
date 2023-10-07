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
package chip.devicecontroller.cluster.structs

import chip.devicecontroller.cluster.*
import chip.tlv.ContextSpecificTag
import chip.tlv.Tag
import chip.tlv.TlvReader
import chip.tlv.TlvWriter

class EnergyManagementClusterSlotAdjustmentStruct(
  val slotIndex: Int,
  val nominalPower: Int,
  val duration: Long
) {
  override fun toString(): String = buildString {
    append("EnergyManagementClusterSlotAdjustmentStruct {\n")
    append("\tslotIndex : $slotIndex\n")
    append("\tnominalPower : $nominalPower\n")
    append("\tduration : $duration\n")
    append("}\n")
  }

  fun toTlv(tag: Tag, tlvWriter: TlvWriter) {
    tlvWriter.apply {
      startStructure(tag)
      put(ContextSpecificTag(TAG_SLOT_INDEX), slotIndex)
      put(ContextSpecificTag(TAG_NOMINAL_POWER), nominalPower)
      put(ContextSpecificTag(TAG_DURATION), duration)
      endStructure()
    }
  }

  companion object {
    private const val TAG_SLOT_INDEX = 0
    private const val TAG_NOMINAL_POWER = 1
    private const val TAG_DURATION = 2

    fun fromTlv(tag: Tag, tlvReader: TlvReader): EnergyManagementClusterSlotAdjustmentStruct {
      tlvReader.enterStructure(tag)
      val slotIndex = tlvReader.getInt(ContextSpecificTag(TAG_SLOT_INDEX))
      val nominalPower = tlvReader.getInt(ContextSpecificTag(TAG_NOMINAL_POWER))
      val duration = tlvReader.getLong(ContextSpecificTag(TAG_DURATION))

      tlvReader.exitContainer()

      return EnergyManagementClusterSlotAdjustmentStruct(slotIndex, nominalPower, duration)
    }
  }
}
