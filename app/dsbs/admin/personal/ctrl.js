
'use strict';

app.controller('PersonalController', function($scope, $resource,$stateParams,$modal,$state,$localStorage) {
	//设置user值
    var $com = $resource($scope.app.host + "/user/index/:id/?",{id:'@id'});
    var resp = $com.get({id:$localStorage.user.id},function(data){
        $scope.user = data.object;
    });

    $scope.submit = function(){
      	var $com = $resource($scope.app.host + "/user/index/:id/?",{id:'@id'},{
            'update': { method:'PUT' },
        });
        $com.update({id:$localStorage.user.id},$scope.user,function(data){
            $localStorage.user = $scope.user;
            $state.go('app.dashboard');
        });
  	};
});