/*
 *
 *    Copyright (c) 2023 Project CHIP Authors
 *    All rights reserved.
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

#include "DefaultEvseManagementDelegate.h"

using namespace chip::app::Clusters::EvseManagement;

CHIP_ERROR DefaultEvseManagementDelegate::DisableEvse()
{
    ChipLogProgress(NotSpecified, "Evse DisableEvse not implemented");
    return CHIP_ERROR_NOT_IMPLEMENTED;
}

CHIP_ERROR DefaultEvseManagementDelegate::EnableEvseCharging(const chip::app::DataModel::Nullable<uint16_t> & evseEnableTime,
                                                             const uint16_t & minimumChargeCurrent,
                                                             const uint16_t & maximumChargeCurrent)
{
    ChipLogProgress(NotSpecified, "Evse EnableEvseCharging not implemented");
    return CHIP_ERROR_NOT_IMPLEMENTED;
}

CHIP_ERROR DefaultEvseManagementDelegate::EnableEvseDischarging(const chip::app::DataModel::Nullable<uint16_t> & evseEnableTime,
                                                                const uint16_t & maximumDischargeCurrent)
{
    ChipLogProgress(NotSpecified, "Evse EnableEvseDischarging not implemented");
    return CHIP_ERROR_NOT_IMPLEMENTED;
}

CHIP_ERROR DefaultEvseManagementDelegate::StartDiagnostics()
{
    ChipLogProgress(NotSpecified, "Evse Diagnostics not implemented");
    return CHIP_ERROR_NOT_IMPLEMENTED;
}