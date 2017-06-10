
'use strict';

app.controller('WarehousingController', function($scope, $resource,$stateParams,$modal,$state,$localStorage) {
	$scope.query = function(page,filter){

        var $com = $resource($scope.app.host + "/material/?page=:page&search=:filter",{page:'@page',filter:'@filter'});
        if(!page){
            page=1;
        }else{
            page=parseInt(page);
        }
        $com.get({page:page,filter:filter},function(data){
            console.log(data);
            data.total = 10;
            //计算页面数，默认每页10条记录
            if(data.total / 10 * 10 == data.total){
              data.page_count = data.total / 10;
            }
            else{
              data.page_count = data.total / 10 + 1;
            }

            //扩展分页数据，显示页签，最终效果为  < 1 2 3 4 5 >
            data.page_index = page;
            data.pages = [];    //页签表
            var N = 5;          //每次显示5个页签
            var s = Math.floor(page/N)*N;
            if(s==page)s-=N;
            s += 1;
            var e = Math.min(data.page_count,s+N-1)
            for(var i=s;i<=e;i++)
                data.pages.push(i);
            $scope.data = data;
            $scope.search_context = filter;
        }
      );
    }

    //根据url参数（分页、搜索关键字）查询数据
    $scope.query($stateParams.page,$stateParams.search);

    $scope.addUpdate = function(data){
        console.log(data);
        //添加入库
        data.materialNumber = data.addNumber + data.materialNumber;

        //更新
        var $com = $resource($scope.app.host + "/material/:id/?",{id:'@id'},{
            'update': { method:'PUT' },
        });
        $com.update({id:data.id},data,function(data){
            $state.go('app.warehousing.list');
        });
    }
});

app.controller('WarehousingCreateController', function($rootScope,$scope, $resource, $stateParams,$state) {
    $scope.data = {};

    $scope.submit = function(){
        var $com = $resource($scope.app.host + "/material/?");
        $com.save($scope.data,function(data){
            $state.go('app.warehousing.list');
        });
    };
    $scope.cancel = function(){
        $state.go('app.warehousing.list');
    }
});