import {NativeModules} from 'react-native';

const {RNRnNetworkPrinter, Network} = NativeModules;
const PrintText = async (ip, text) => {
  return new Promise((resolve, reject) => {
    RNRnNetworkPrinter.PrintText(ip, text, (err, result) => {
      if (err) reject(err);
      resolve(result);
    });
  });
};
const PrintPic = async (ip, base64, width) => {
  return new Promise((resolve, reject) => {
    RNRnNetworkPrinter.PrintPic(ip, base64, {width: width || 210}, (err, result) => {
      if (err) reject(err);
      resolve(result);
    });
  });
};
const GetPrinters = async () => {
  const data = await Promise.all([getPrinter(0), getPrinter(1), getPrinter(2)]);
  return data[0].concat(data[1]).concat(data[2]);
};
const CutPaper = async (ip) => {
  return new Promise((resolve, reject) => {
    RNRnNetworkPrinter.CutPaper(ip, (err, result) => {
      if (err) reject(err);
      resolve(result);
    });
  });
};
const getPrinter = async type => {
  return new Promise((resolve, reject) => {
    Network.list(type || 0, (err, result) => {
      if (err) reject(err);
      resolve(result);
    });
  });
};
export {PrintPic, PrintText, CutPaper, GetPrinters};
