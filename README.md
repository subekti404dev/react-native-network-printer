
# react-native-network-printer

## Getting started

`$ npm install github:copas12/react-native-network-printer --save`

### Mostly automatic installation

`$ react-native link react-native-network-printer`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-network-printer` and add `RNRnNetworkPrinter.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNRnNetworkPrinter.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.subekti.RNNetworkPrinter.RNRnNetworkPrinterPackage;` to the imports at the top of the file
  - Add `new RNRnNetworkPrinterPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-network-printer'
  	project(':react-native-network-printer').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-network-printer/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-network-printer')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNRnNetworkPrinter.sln` in `node_modules/react-native-network-printer/windows/RNRnNetworkPrinter.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Rn.Network.Printer.RNRnNetworkPrinter;` to the usings at the top of the file
  - Add `new RNRnNetworkPrinterPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import {PrintText, PrintPic, GetPrinters} from 'react-native-network-printer';

// TODO: What to do with the module?
RNRnNetworkPrinter;
```
  