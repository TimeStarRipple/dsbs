'use strict';

app
  .run(
      function ($rootScope,   $state,   $stateParams, $http, $localStorage) {
          $http.defaults.headers.common['Authorization'] = 'Basic ' + $localStorage.auth;
          
          $rootScope.$state = $state;
          $rootScope.$stateParams = $stateParams;

          $rootScope.$on('$stateChangeSuccess', function(event, to, toParams, from, fromParams) {
            $rootScope.previousState = from;
            $rootScope.previousStateParams = fromParams;
          });        
      }
  )
.config(
      function ($stateProvider,   $urlRouterProvider) {
          $urlRouterProvider
              .otherwise('/auth/loading');

          // $urlRouterProvider
          //     .otherwise('/app/dashboard');

          $stateProvider
              .state('app', {
                  abstract: true,
                  url: '/app',
                  templateUrl: 'admin/app.html'
              })
              .state('app.dashboard', {
                  url: '/dashboard',
                  templateUrl: 'admin/dashboard.html',
                  ncyBreadcrumb: {
                    label: '<i class="fa fa-home"></i> 首页'
                  }
              });

          $stateProvider
              .state('auth',{
                  abstract: true,
                  url:'/auth',
                  template: '<div ui-view class="fade-in"></div>',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('admin/auth/ctrl.js');
                      }]
                  }
              })
              .state('auth.loading',{
                  url:'/loading',
                  templateUrl:'admin/auth/loading.html',
              })
              .state('auth.login',{
                  url:'/login',
                  templateUrl:'admin/auth/login.html',
              })

          .state('app.material', {
                  abstract: true,
                  url: '/material',
                  template: '<div ui-view class="fade-in"></div>',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('admin/material/ctrl.js');
                      }]
                  }
                  
              })
              .state('app.material.list', {
                  url: '/list?page&search',
                  templateUrl: 'admin/material/list.html',
                  ncyBreadcrumb: {
                    parent:'app.dashboard',
                    label: '材料列表'
                  }
              })
              .state('app.material.detail', {
                  url: '/detail/{id}',
                  templateUrl: 'admin/material/detail.html',
                  ncyBreadcrumb: {
                    parent:'app.material.list',
                    label: '材料编辑',
                  }
              })
              .state('app.material.create', {
                  url: '/create',
                  templateUrl: 'admin/material/detail.html',
                  ncyBreadcrumb: {
                    parent:'app.material.list',
                    label: '材料新增'
                  }
              })
          .state('app.activiti', {
                  abstract: true,
                  url: '/activiti',
                  template: '<div ui-view class="fade-in"></div>',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('admin/activiti/ctrl.js');
                      }]
                  }
                  
              })
              .state('app.activiti.list', {
                  url: '/list?page&search&roleId',
                  templateUrl: 'admin/activiti/list.html',
                  ncyBreadcrumb: {
                    parent:'app.dashboard',
                    label: '我的工作流列表'
                  }
              })
              .state('app.activiti.detail', {
                  url: '/detail/{id}',
                  templateUrl: 'admin/activiti/detail.html',
                  ncyBreadcrumb: {
                    parent:'app.activiti.list',
                    label: '编辑产品投标',
                  }
              })
              .state('app.activiti.create', {
                  url: '/create',
                  templateUrl: 'admin/activiti/detail.html',
                  ncyBreadcrumb: {
                    parent:'app.activiti.list',
                    label: '创建产品投标'
                  }
              })
              .state('app.activiti.productSplit', {
                  url: '/productSplit',
                  params: {'id': null},
                  templateUrl: 'admin/activiti/productSplitDetail.html',
                  ncyBreadcrumb: {
                    parent:'app.activiti.list',
                    label: '技术部门-产品拆分'
                  }
              })
              .state('app.activiti.confirmQuantity', {
                  url: '/confirmQuantity/{id}',
                  templateUrl: 'admin/activiti/confirmQuantityDetail.html',
                  ncyBreadcrumb: {
                    parent:'app.activiti.list',
                    label: '仓库部门-确认数量'
                  }
              })
              .state('app.activiti.determineCost', {
                  url: '/determineCost/{id}',
                  templateUrl: 'admin/activiti/determineCostDetail.html',
                  ncyBreadcrumb: {
                    parent:'app.activiti.list',
                    label: '生产部门-确定费用'
                  }
              })
              .state('app.activiti.determinePurchasePrice', {
                  url: '/determinePurchasePrice/{id}',
                  templateUrl: 'admin/activiti/determinePurchasePriceDetail.html',
                  ncyBreadcrumb: {
                    parent:'app.activiti.list',
                    label: '采购部门-确定收购价'
                  }
              })
              .state('app.activiti.quoteResults', {
                  url: '/quoteResults/{id}',
                  templateUrl: 'admin/activiti/quoteResultsDetail.html',
                  ncyBreadcrumb: {
                    parent:'app.activiti.list',
                    label: '查看报价结果'
                  }
              })
          .state('app.personal', {
                  abstract: true,
                  url: '/personal',
                  template: '<div ui-view class="fade-in"></div>',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('admin/personal/ctrl.js');
                      }]
                  }
                  
              })
              .state('app.personal.information', {
                  url: '/information',
                  templateUrl: 'admin/personal/detail.html',
                  ncyBreadcrumb: {
                    parent:'app.dashboard',
                    label: '个人信息'
                  }
              })
          .state('app.informationQuery', {
                  abstract: true,
                  url: '/informationQuery',
                  template: '<div ui-view class="fade-in"></div>',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('admin/informationQuery/ctrl.js');
                      }]
                  }
                  
              })
              .state('app.informationQuery.list', {
                  url: '/list',
                  templateUrl: 'admin/informationQuery/list.html',
                  ncyBreadcrumb: {
                    parent:'app.dashboard',
                    label: '材料信息查询'
                  }
              })
          .state('app.account', {
                  abstract: true,
                  url: '/account',
                  template: '<div ui-view class="fade-in"></div>',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('admin/account/ctrl.js');
                      }]
                  }
                  
              })
              .state('app.account.list', {
                  url: '/list?page&search',
                  templateUrl: 'admin/account/list.html',
                  ncyBreadcrumb: {
                    parent:'app.dashboard',
                    label: '账户信息列表'
                  }
              })
              .state('app.account.detail', {
                  url: '/detail/{id}',
                  templateUrl: 'admin/account/detail.html',
                  ncyBreadcrumb: {
                    parent:'app.account.list',
                    label: '账户详细信息'
                  }
              })
          .state('app.warehousing', {
                  abstract: true,
                  url: '/warehousing',
                  template: '<div ui-view class="fade-in"></div>',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('admin/warehousing/ctrl.js');
                      }]
                  }
                  
              })
              .state('app.warehousing.list', {
                  url: '/list?page&search',
                  templateUrl: 'admin/warehousing/list.html',
                  ncyBreadcrumb: {
                    parent:'app.dashboard',
                    label: '入库管理列表'
                  }
              })
              .state('app.warehousing.create', {
                  url: '/create',
                  templateUrl: 'admin/warehousing/create.html',
                  ncyBreadcrumb: {
                    parent:'app.dashboard',
                    label: '新增入库材料'
                  }
              })
          .state('app.outOfTheLibrary', {
                  abstract: true,
                  url: '/outOfTheLibrary',
                  template: '<div ui-view class="fade-in"></div>',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('admin/outOfTheLibrary/ctrl.js');
                      }]
                  }
                  
              })
              .state('app.outOfTheLibrary.list', {
                  url: '/list?page&search',
                  templateUrl: 'admin/outOfTheLibrary/list.html',
                  ncyBreadcrumb: {
                    parent:'app.dashboard',
                    label: '出库管理列表'
                  }
              })
          .state('app.materialPrice', {
                  abstract: true,
                  url: '/materialPrice',
                  template: '<div ui-view class="fade-in"></div>',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('admin/materialPrice/ctrl.js');
                      }]
                  }
                  
              })
              .state('app.materialPrice.list', {
                  url: '/list?page&search',
                  templateUrl: 'admin/materialPrice/list.html',
                  ncyBreadcrumb: {
                    parent:'app.dashboard',
                    label: '材料价格管理列表'
                  }
              })
          .state('app.databaseBackup', {
                  abstract: true,
                  url: '/databaseBackup',
                  template: '<div ui-view class="fade-in"></div>',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('admin/databaseBackup/ctrl.js');
                      }]
                  }
                  
              })
              .state('app.databaseBackup.list', {
                  url: '/list?page&search',
                  templateUrl: 'admin/databaseBackup/list.html',
                  ncyBreadcrumb: {
                    parent:'app.dashboard',
                    label: '数据库备份管理列表'
                  }
              })
              .state('app.databaseBackup.detail', {
                  url: '/detail/{id}',
                  templateUrl: 'admin/databaseBackup/detail.html',
                  ncyBreadcrumb: {
                    parent:'app.databaseBackup.list',
                    label: '账户详细信息'
                  }
              })
      }
  );

// 考虑到账号更改密码，需要让他重新登录
app.config(function ($httpProvider) {
  $httpProvider.interceptors.push('AuthInterceptor');
});
app.factory('AuthInterceptor', function ($rootScope, $q,$location) {
  return {
    responseError: function (response) {
        if(response.status==401 || response.status==0)
        {
            $location.url('/auth/login');
        }
      return $q.reject(response);
    }
  };
});

//跨域设置
app.config(['$httpProvider', config]);  
  
function config($httpProvider) {  
    $httpProvider.defaults.withCredentials = true;  
    $httpProvider.defaults.headers.common = { 'Access-Control-Allow-Origin' : '*' }  
} 
  
// 定义一个 Service ，稍等将会把它作为 Interceptors 的处理函数
// app.factory('HttpInterceptor', ['$q', HttpInterceptor]);

// function HttpInterceptor($q) {
//   return {
//     request: function(config){
//       return config;
//     },
//     requestError: function(err){
//       return $q.reject(err);
//     },
//     response: function(res){
//       return res;
//     },
//     responseError: function(err){
//       console.log(22222333);
//       console.log(err);
//       console.log(3333333)
//       if(-1 === err.status) {
//         // 远程服务器无响应
//       } else if(500 === err.status) {
//         // 处理各类自定义错误
//       } else if(501 === err.status) {
//         // ...
//       }
//       return $q.reject(err);
//     }
//   };
// }

// // 添加对应的 Interceptors
// app.config(['$httpProvider', function($httpProvider){
//   $httpProvider.interceptors.push(HttpInterceptor);
// }]);