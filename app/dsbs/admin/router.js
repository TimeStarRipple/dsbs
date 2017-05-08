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