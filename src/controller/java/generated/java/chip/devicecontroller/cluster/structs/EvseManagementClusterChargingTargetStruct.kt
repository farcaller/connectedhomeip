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
import java.util.Optional

class EvseManagementClusterChargingTargetStruct(
  val targetTime: Int,
  val targetSoC: Optional<Int>,
  val addedEnergy: Optional<Long>
) {
  override fun toString(): String = buildString {
    append("EvseManagementClusterChargingTargetStruct {\n")
    append("\ttargetTime : $targetTime\n")
    append("\ttargetSoC : $targetSoC\n")
    append("\taddedEnergy : $addedEnergy\n")
    append("}\n")
  }

  fun toTlv(tag: Tag, tlvWriter: TlvWriter) {
    tlvWriter.apply {
      startStructure(tag)
      put(ContextSpecificTag(TAG_TARGET_TIME), targetTime)
      if (targetSoC.isPresent) {
        val opttargetSoC = targetSoC.get()
        put(ContextSpecificTag(TAG_TARGET_SO_C), opttargetSoC)
      }
      if (addedEnergy.isPresent) {
        val optaddedEnergy = addedEnergy.get()
        put(ContextSpecificTag(TAG_ADDED_ENERGY), optaddedEnergy)
      }
      endStructure()
    }
  }

  companion object {
    private const val TAG_TARGET_TIME = 0
    private const val TAG_TARGET_SO_C = 1
    private const val TAG_ADDED_ENERGY = 2

    fun fromTlv(tag: Tag, tlvReader: TlvReader): EvseManagementClusterChargingTargetStruct {
      tlvReader.enterStructure(tag)
      val targetTime = tlvReader.getInt(ContextSpecificTag(TAG_TARGET_TIME))
      val targetSoC =
        if (tlvReader.isNextTag(ContextSpecificTag(TAG_TARGET_SO_C))) {
          Optional.of(tlvReader.getInt(ContextSpecificTag(TAG_TARGET_SO_C)))
        } else {
          Optional.empty()
        }
      val addedEnergy =
        if (tlvReader.isNextTag(ContextSpecificTag(TAG_ADDED_ENERGY))) {
          Optional.of(tlvReader.getLong(ContextSpecificTag(TAG_ADDED_ENERGY)))
        } else {
          Optional.empty()
        }

      tlvReader.exitContainer()

      return EvseManagementClusterChargingTargetStruct(targetTime, targetSoC, addedEnergy)
    }
  }
}
