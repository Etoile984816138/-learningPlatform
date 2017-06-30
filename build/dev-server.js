require('./check-versions')()

var config = require('../config')
if (!process.env.NODE_ENV) {
  process.env.NODE_ENV = JSON.parse(config.dev.env.NODE_ENV)
}

var opn = require('opn')
var path = require('path')
var express = require('express')
var webpack = require('webpack')
var proxyMiddleware = require('http-proxy-middleware')
var webpackConfig = require('./webpack.dev.conf')

// default port where dev server listens for incoming traffic
var port = process.env.PORT || config.dev.port
// automatically open browser, if not set will be false
var autoOpenBrowser = !!config.dev.autoOpenBrowser
// Define HTTP proxies to your custom API backend
// https://github.com/chimurai/http-proxy-middleware
var proxyTable = config.dev.proxyTable

var app = express()

var appData = require('../data.json');
var direction = appData.direction,
    generics = appData.generics,
    course = appData.course,
    news = appData.new,
    user = appData.user,
    style = appData.style,
    generic = appData.generic,
    courseDetail = appData.courseDetail,
    chapter = appData.chapter,
    courseAdd = appData.courseAdd,
    discusion = appData.discusion

var apiRoutes = express.Router();

apiRoutes.param('id', function (req, res, next, id) {
  next();
});

// 根据方向获取分类
apiRoutes.get('/direction/:id', function (req, res) {
  res.json({
    failure: [],
    data: direction
  });
  res.end();
});



// 获取所有课程分类
apiRoutes.get('/generic', function (req, res) {
  res.json({
    failure: [],
    success: generics.success
  });
});

// 检索功能
apiRoutes.get('/course', function (req, res) {
  res.json({
    failure: [],
    success: course.success
  });
});

// 获取最新课程
apiRoutes.get('/course/new', function (req, res) {
  res.json({
    failure: [],
    success: news.success
  });
});

// 获取个人详细信息
apiRoutes.get('/user', function (req, res) {
  res.json({
    failure: [],
    success: user.success
  });
});

// 获取所有方向
apiRoutes.get('/direction', function (req, res) {
  res.json({
    failure: [],
    success: direction.success
  });
});

// 获取所有课程类型
apiRoutes.get('/style', function (req, res) {
  res.json({
    failure: [],
    success: style.success
  });
});

// 根据课程类别获取课程类型
apiRoutes.get('/generic/:generic_id', function (req, res) {
  res.json({
    failure: [],
    success: generic.success
  });
});

// 获取课程详情
apiRoutes.get('/course/:c_id', function (req, res) {
  res.json({
    failure: [],
    success: courseDetail.success
  });
});

// 获取章节
apiRoutes.get('/course/:c_id/section', function (req, res) {
  res.json({
    failure: [],
    success: chapter.success
  });
});

// 加入课程
apiRoutes.get('/course/add/:c_id', function (req, res) {
  res.json({
    failure: [],
    success: courseAdd.success
  });
});
app.use('/',apiRoutes);

// 获取讨论区数据
apiRoutes.get('/discuss/:course_id/course/:type', function (req, res) {
  res.json({
    failure: [],
    success: discusion.success
  });
});
app.use('/',apiRoutes);


var compiler = webpack(webpackConfig)

var devMiddleware = require('webpack-dev-middleware')(compiler, {
  publicPath: webpackConfig.output.publicPath,
  quiet: true
})

var hotMiddleware = require('webpack-hot-middleware')(compiler, {
  log: () => {}
})
// force page reload when html-webpack-plugin template changes
compiler.plugin('compilation', function (compilation) {
  compilation.plugin('html-webpack-plugin-after-emit', function (data, cb) {
    hotMiddleware.publish({ action: 'reload' })
    cb()
  })
})

// proxy api requests
Object.keys(proxyTable).forEach(function (context) {
  var options = proxyTable[context]
  if (typeof options === 'string') {
    options = { target: options }
  }
  app.use(proxyMiddleware(options.filter || context, options))
})

// handle fallback for HTML5 history API
app.use(require('connect-history-api-fallback')())

// serve webpack bundle output
app.use(devMiddleware)

// enable hot-reload and state-preserving
// compilation error display
app.use(hotMiddleware)

// serve pure static assets
var staticPath = path.posix.join(config.dev.assetsPublicPath, config.dev.assetsSubDirectory)
app.use(staticPath, express.static('./static'))

var uri = 'http://localhost:' + port

var _resolve
var readyPromise = new Promise(resolve => {
  _resolve = resolve
})

console.log('> Starting dev server...')
devMiddleware.waitUntilValid(() => {
  console.log('> Listening at ' + uri + '\n')
  // when env is testing, don't need open it
  if (autoOpenBrowser && process.env.NODE_ENV !== 'testing') {
    opn(uri)
  }
  _resolve()
})

var server = app.listen(port)

module.exports = {
  ready: readyPromise,
  close: () => {
    server.close()
  }
}