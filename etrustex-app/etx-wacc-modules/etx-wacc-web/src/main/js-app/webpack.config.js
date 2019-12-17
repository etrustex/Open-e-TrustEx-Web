const path = require('path');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const basePath = __dirname;
const targetFolder = 'dist';
const webappRoot = basePath + '/../webapp/js';

module.exports = {
  entry: {
    upload: [/*'babel-polyfill', */'./src/upload/messageEdit.js'],
    download:  [/*'babel-polyfill', */'./src/download/downloadModule.js'],
    i18: './src/common/i18.js',
    common: './src/common/common.js',
    crypto: './src/crypto/pkcsReader.js'
    /*admin: './src/admin/admin.js'*/
  },
  devtool: 'inline-source-map',
  plugins: [
    new CleanWebpackPlugin([targetFolder], {
      root: webappRoot,
      verbose: true
    }),
  ],
  output: {
    filename: '[name].bundle.js',
    path: path.resolve(basePath, webappRoot + '/' + targetFolder),
    libraryTarget: 'var',
    library: ['GlobalAccess', '[name]']
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ["env"]
          }
        }
      }
    ]
  }/*,
  resolve: {
    alias: {
      jquery: "jquery/src/jquery"
    }
  }*/
};