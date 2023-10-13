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

#include "evse-management-delegate-impl.h"
#include "evse-management-manager.h"
#include "app/clusters/evse-management-server/evse-management-server.h"

#include <lib/support/logging/CHIPLogging.h>

using namespace chip;
using namespace chip::app;
using namespace chip::app::Clusters;
using namespace chip::app::Clusters::EvseManagement;


EvseManagementManager EvseManagementManager::sEvseManagement;
static EvseManagementDelegate *  gEvseManagementDelegate = nullptr;

void EvseManagementManager::Shutdown()
{
    if (gEvseManagementDelegate != nullptr)
    {
        delete gEvseManagementDelegate;
        gEvseManagementDelegate = nullptr;
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
    EvseManagement::SetDefaultDelegate(gEvseManagementDelegate);

    // what are these options? :  AppServer,NotSpecified,Zcl
    ChipLogProgress(Zcl, "emberAfEvseManagementManagerClusterInitCallback, endpoint=%d", endpointId);
}
