var ExtractTextPlugin = require("extract-text-webpack-plugin");//Css分割插件

module.exports = {
   context: __dirname + '/dsbs',//上下文
   entry: './js/app.js',//入口文件
   output: {//输出文件
     path: __dirname + '/dsbs',
     filename: './bundle.js'
   },
   module: {
     loaders: [{
                test: /\.html$/,
                loader: 'raw'
            }, {
                test: /\.(png|jpg|gif)$/,
                loader: 'url?limit=8192,name=/img/[name].[hash:6].[ext]'
            }, {
                test: /\.(woff|woff2|eot|ttf|svg)(\?.*$|$)/,
                loader: 'url-loader?importLoaders=1&limit=1000&name=/fonts/[name].[ext]'
            }, {
                test: /\.css$/,
                loaders: ['style', 'css']
            }, { //外链
                test: /\.scss$/,
                loader: ExtractTextPlugin.extract("style-loader", "css-loader!sass-loader!postcss-loader")
            }
     ]
   },
   plugins: [
    new ExtractTextPlugin("/css/styles.[hash:6].css")
   ]
};