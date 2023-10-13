/*
 *
 *    Copyright (c) 2023 Project CHIP Authors
 *    Copyright (c) 2019 Google LLC.
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

#include "EvseManagementManager.h"
#include <app/clusters/evse-management-server/evse-management-server.h>

#include <lib/support/logging/CHIPLogging.h>

EvseManagementManager EvseManagementManager::sEvseManagement;
static EvseManagementDelegate *  gEvseManagementDelegate = nullptr;

void EvseManagementManager::Shutdown()
{
    if (gEvseManagementManagerDelegate != nullptr)
    {
        delete gEvseManagementManagerDelegate;
        gEvseManagementManagerDelegate = nullptr;
    }
}

CHIP_ERROR EvseManagementManager::Init()
{
    return CHIP_NO_ERROR;
}

void emberAfEvseManagementManagerClusterInitCallback(chip::EndpointId endpointId)
{
    VerifyOrDie(endpointId == 1);       // this cluster is only enabled for endpoint 1 ??
    gEvseManagementDelegate  = new EvseManagementDelegate;
    // what are these options? :  AppServer,NotSpecified,Zcl
    ChipLogProgress(Zcl, "emberAfEvseManagementManagerClusterInitCallback, endpoint=%d", endpointId);
}

bool emberAfEvseManagementClusterDisableEvseChargingCallback(
                chip::app::CommandHandler * commandObj,
                const chip::app::ConcreteCommandPath & commandPath,
                const chip::app::Clusters::EvseManagement::Commands::DisableEvseCharging::DecodableType & commandData)
{
    bool status = true;
    ChipLogProgress(Zcl, "emberAfEvseManagementClusterDisableEvseChargingCallback, endpoint=%d", endpointId);
    return status;
}

bool emberAfEvseManagementClusterEnsableEvseChargingCallback(
                chip::app::CommandHandler * commandObj,
                const chip::app::ConcreteCommandPath & commandPath,
                const chip::app::Clusters::EvseManagement::Commands::EnsableEvseCharging::DecodableType & commandData)
{
    bool status = true;
    ChipLogProgress(Zcl, "emberAfEvseManagementClusterEnsableEvseChargingCallback, endpoint=%d", endpointId);
    return status;
}

bool emberAfEvseManagementClusterEnableEvseDischargingCallback(
                chip::app::CommandHandler * commandObj,
                const chip::app::ConcreteCommandPath & commandPath,
                const chip::app::Clusters::EvseManagement::Commands::EnableEvseDischarging::DecodableType & commandData)
{
    bool status = true;
    ChipLogProgress(Zcl, "emberAfEvseManagementClusterEnableEvseDischargingCallback, endpoint=%d", endpointId);
    return status;
}

bool emberAfEvseManagementClusterStartDiagnosticsCallback(
                chip::app::CommandHandler * commandObj,
                const chip::app::ConcreteCommandPath & commandPath,
                const chip::app::Clusters::EvseManagement::Commands::StartDiagnostics::DecodableType & commandData)
{
    bool status = true;
    ChipLogProgress(Zcl, "emberAfEvseManagementClusterStartDiagnosticsCallback, endpoint=%d", endpointId);
    return status;
}
