var path = require('path')
var utils = require('./utils')
var config = require('../config')
var vueLoaderConfig = require('./vue-loader.conf')
var ExtractTextPlugin = require('extract-text-webpack-plugin')
console.log(ExtractTextPlugin)

function resolve(dir) {
    return path.join(__dirname, '..', dir)
}

function getEntry(globPath) {
    var entries = {},
        basename, tmp, pathname;

    glob.sync(globPath).forEach(function(entry) {
        basename = path.basename(entry, path.extname(entry));
        tmp = entry.split('/').splice(-3);
        pathname = tmp.splice(0, 1) + '/' + basename; // 正确输出js和html的路径
        entries[pathname] = entry;
    });
    console.log("base-entrys:");
    console.log(entries);
    return entries;
}
var glob = require('glob');
var entries = getEntry('./src/module/**/*.js'); // 获得入口js文件
module.exports = {
    entry: entries,
    output: {
        path: config.build.assetsRoot,
        filename: '[name].js',
        publicPath: process.env.NODE_ENV === 'production' ? config.build.assetsPublicPath : config.dev.assetsPublicPath
    },
    resolve: {
        extensions: ['.js', '.vue', '.json'],
        alias: {
            'muse-components': resolve('node_modules/muse-ui/src'),
            // 'muse-style': 'muse-ui/src/styles/base.less',
            'vue$': 'vue/dist/vue.esm.js',
            '@': resolve('src'),
            'assets': resolve('src/assets'),
            'components': resolve('src/components'),
            'common': resolve('src/common'),
            'commonComp': resolve('src/components/common'),
            'entry': resolve('src/components/entry')
        }
    },
    module: {
        rules: [{
            test: /muse-ui.src.*?js$/,
            loader: 'babel-loader'
        }, {
            test: /\.vue$/,
            loader: 'vue-loader',
            options: vueLoaderConfig
        }, {
            test: /\.js$/,
            loader: 'babel-loader',
            include: [resolve('src'), resolve('test')]
        }, {
            test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
            loader: 'url-loader',
            options: {
                limit: 10000,
                name: utils.assetsPath('img/[name].[hash:7].[ext]')
            }
        }, {
            test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
            loader: 'url-loader',
            options: {
                limit: 10000,
                name: utils.assetsPath('fonts/[name].[hash:7].[ext]')
            }
        }, {
            test: /\.(mp3|mp4|pptx|pdf)(\?.*)?$/,
            loader: 'url-loader',
            options: {
                limit: 10000,
                name: utils.assetsPath('media/[name].[hash:7].[ext]')
            }
        }]
    }
}
