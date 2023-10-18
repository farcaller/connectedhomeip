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

#pragma once

#include "app/clusters/evse-management-server/evse-management-delegate.h"
//  #include <app/clusters/evse-management-server/evse-management-server.h>

#include <app/util/af.h>
#include <app/util/config.h>
#include <cstring>

namespace chip {
namespace app {
namespace Clusters {
namespace EvseManagement {

/**
 * The application delegate.
 */

class EvseManagementDelegate : public Delegate
{
public:
    EvseManagementDelegate() : Delegate(){};

    virtual CHIP_ERROR DisableEvse() override;
    virtual CHIP_ERROR EnableEvseCharging(const chip::app::DataModel::Nullable<uint16_t> & evseEnableTime,
                                          const uint16_t & minimumChargeCurrent, const uint16_t & maximumChargeCurrent) override;
    virtual CHIP_ERROR EnableEvseDischarging(const chip::app::DataModel::Nullable<uint16_t> & evseEnableTime,
                                             const uint16_t & maximumDischargeCurrent) override;
    virtual CHIP_ERROR StartDiagnostics() override;

private:
    static EvseManagementDelegate sInstance;
};

} // namespace LaundryWasherControls
} // namespace Clusters
} // namespace app
} // namespace chip
