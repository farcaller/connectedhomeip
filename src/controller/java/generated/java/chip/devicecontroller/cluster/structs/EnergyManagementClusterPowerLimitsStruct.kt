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
import chip.tlv.AnonymousTag
import chip.tlv.ContextSpecificTag
import chip.tlv.Tag
import chip.tlv.TlvParsingException
import chip.tlv.TlvReader
import chip.tlv.TlvWriter

import java.util.Optional

class EnergyManagementClusterPowerLimitsStruct (
    val startTime: Long,
    val duration: Long,
    val nominalPower: Int,
    val maximumEnergy: Long) {
  override fun toString(): String  = buildString {
    append("EnergyManagementClusterPowerLimitsStruct {\n")
    append("\tstartTime : $startTime\n")
    append("\tduration : $duration\n")
    append("\tnominalPower : $nominalPower\n")
    append("\tmaximumEnergy : $maximumEnergy\n")
    append("}\n")
  }

  fun toTlv(tag: Tag, tlvWriter: TlvWriter) {
    tlvWriter.apply {
      startStructure(tag)
      put(ContextSpecificTag(TAG_START_TIME), startTime)
      put(ContextSpecificTag(TAG_DURATION), duration)
      put(ContextSpecificTag(TAG_NOMINAL_POWER), nominalPower)
      put(ContextSpecificTag(TAG_MAXIMUM_ENERGY), maximumEnergy)
      endStructure()
    }
  }

  companion object {
    private const val TAG_START_TIME = 0
    private const val TAG_DURATION = 1
    private const val TAG_NOMINAL_POWER = 2
    private const val TAG_MAXIMUM_ENERGY = 3

    fun fromTlv(tag: Tag, tlvReader: TlvReader) : EnergyManagementClusterPowerLimitsStruct {
      tlvReader.enterStructure(tag)
      val startTime = tlvReader.getLong(ContextSpecificTag(TAG_START_TIME))
      val duration = tlvReader.getLong(ContextSpecificTag(TAG_DURATION))
      val nominalPower = tlvReader.getInt(ContextSpecificTag(TAG_NOMINAL_POWER))
      val maximumEnergy = tlvReader.getLong(ContextSpecificTag(TAG_MAXIMUM_ENERGY))
      
      tlvReader.exitContainer()

      return EnergyManagementClusterPowerLimitsStruct(startTime, duration, nominalPower, maximumEnergy)
    }
  }
}
