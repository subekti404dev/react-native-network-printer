using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Rn.Network.Printer.RNRnNetworkPrinter
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNRnNetworkPrinterModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNRnNetworkPrinterModule"/>.
        /// </summary>
        internal RNRnNetworkPrinterModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNRnNetworkPrinter";
            }
        }
    }
}
