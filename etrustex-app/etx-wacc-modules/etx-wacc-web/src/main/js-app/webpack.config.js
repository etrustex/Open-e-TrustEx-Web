const path = require('path');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const basePath = __dirname;
const targetFolder = 'dist';
const webappRoot = basePath + '/../webapp/js';

module.exports = {
  entry: {
    upload: './src/upload/messageEdit.js',
    download: './src/download/downloadModule.js'
    /*admin: './src/admin/admin.js'
    common: './src/common/common.js'*/
  },
  devtool: 'inline-source-map',
  plugins: [
    new CleanWebpackPlugin([targetFolder], {
      root: webappRoot,
      verbose: true
    })
  ],
  output: {
    filename: '[name].bundle.js',
    path: path.resolve(basePath, webappRoot + '/' + targetFolder)
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader'/*,
          options: {
            presets: ['es2015']
          }*/
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