
'use strict';

app.controller('DatabaseBackupController', function($scope, $resource,$stateParams,$modal,$state,$localStorage) {
	$scope.query = function(page,filter){

        page = 1;
        var data = {};
        data.total = 1;
        data.page_count = 1;
        data.page_index = page;
        data.pages = [1];

        data.object = [{
            id:1,
            name:"初试备份",
            createTime:1461738635081,
            description:"最开始的数据备份"
        }];

        $scope.data = data;
        $scope.search_context = filter;
        
        console.log($scope.data);
    };

    //根据url参数（分页、搜索关键字）查询数据
    $scope.query($stateParams.page,$stateParams.search);
});

app.controller('DatabaseBackupDetailController', function($rootScope,$scope, $resource, $stateParams,$state) {
  $scope.data = {
        id:1,
        name:"初试备份",
        createTime:1461738635081,
        description:"最开始的数据备份"
    };
  $scope.cancel = function(){
      $state.go('app.databaseBackup.list');
  }
});