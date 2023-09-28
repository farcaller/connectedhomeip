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

class EnergyManagementClusterCostStruct(
  val costType: Int,
  val value: Long?,
  val decimalPoints: Int,
  val currency: Optional<Int>
) {
  override fun toString(): String = buildString {
    append("EnergyManagementClusterCostStruct {\n")
    append("\tcostType : $costType\n")
    append("\tvalue : $value\n")
    append("\tdecimalPoints : $decimalPoints\n")
    append("\tcurrency : $currency\n")
    append("}\n")
  }

  fun toTlv(tag: Tag, tlvWriter: TlvWriter) {
    tlvWriter.apply {
      startStructure(tag)
      put(ContextSpecificTag(TAG_COST_TYPE), costType)
      if (value != null) {
        put(ContextSpecificTag(TAG_VALUE), value)
      } else {
        putNull(ContextSpecificTag(TAG_VALUE))
      }
      put(ContextSpecificTag(TAG_DECIMAL_POINTS), decimalPoints)
      if (currency.isPresent) {
        val optcurrency = currency.get()
        put(ContextSpecificTag(TAG_CURRENCY), optcurrency)
      }
      endStructure()
    }
  }

  companion object {
    private const val TAG_COST_TYPE = 0
    private const val TAG_VALUE = 1
    private const val TAG_DECIMAL_POINTS = 2
    private const val TAG_CURRENCY = 3

    fun fromTlv(tag: Tag, tlvReader: TlvReader): EnergyManagementClusterCostStruct {
      tlvReader.enterStructure(tag)
      val costType = tlvReader.getInt(ContextSpecificTag(TAG_COST_TYPE))
      val value =
        if (!tlvReader.isNull()) {
          tlvReader.getLong(ContextSpecificTag(TAG_VALUE))
        } else {
          tlvReader.getNull(ContextSpecificTag(TAG_VALUE))
          null
        }
      val decimalPoints = tlvReader.getInt(ContextSpecificTag(TAG_DECIMAL_POINTS))
      val currency =
        if (tlvReader.isNextTag(ContextSpecificTag(TAG_CURRENCY))) {
          Optional.of(tlvReader.getInt(ContextSpecificTag(TAG_CURRENCY)))
        } else {
          Optional.empty()
        }

      tlvReader.exitContainer()

      return EnergyManagementClusterCostStruct(costType, value, decimalPoints, currency)
    }
  }
}
