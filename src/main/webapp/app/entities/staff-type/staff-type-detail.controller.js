(function() {
    'use strict';

    angular
        .module('masterfaceproApp')
        .controller('StaffTypeDetailController', StaffTypeDetailController);

    StaffTypeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'StaffType'];

    function StaffTypeDetailController($scope, $rootScope, $stateParams, previousState, entity, StaffType) {
        var vm = this;

        vm.staffType = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('masterfaceproApp:staffTypeUpdate', function(event, result) {
            vm.staffType = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
