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

#include <evse-management-delegate-impl.h>
// #include <app/clusters/evse-management-server/evse-management-delegate.h>
#include <app/util/config.h>

using namespace chip;
using namespace chip::app;
using namespace chip::app::Clusters;
using namespace chip::app::Clusters::EvseManagement;

EvseManagementDelegate EvseManagementDelegate::sInstance;


CHIP_ERROR EvseManagementDelegate::DisableEvseCharging() 
{
    ChipLogProgress(AppServer, "EvseManagementDelegate::DisableEvseCharging()");
    return CHIP_NO_ERROR;
}

CHIP_ERROR EvseManagementDelegate::EnableEvseCharging(const chip::app::DataModel::Nullable<uint16_t> & evseEnableTime,
                                                      const uint16_t & minimumChargeCurrent, const uint16_t & maximumChargeCurrent)
{
    ChipLogProgress(AppServer, "EvseManagementDelegate::EnableEvseCharging()");
    return CHIP_NO_ERROR;
}

CHIP_ERROR EvseManagementDelegate::EnableEvseDischarging(const chip::app::DataModel::Nullable<uint16_t> & evseEnableTime,
                                                        const uint16_t & maximumDischargeCurrent)
{
    ChipLogProgress(AppServer, "EvseManagementDelegate::EnableEvseDischarging()");
    return CHIP_NO_ERROR;
}

CHIP_ERROR EvseManagementDelegate::StartDiagnostics()
{
    ChipLogProgress(AppServer, "EvseManagementDelegate::StartDiagnostics()");
    return CHIP_NO_ERROR;
}
