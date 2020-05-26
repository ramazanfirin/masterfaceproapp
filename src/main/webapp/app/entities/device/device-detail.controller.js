(function() {
    'use strict';

    angular
        .module('masterfaceproApp')
        .controller('DeviceDetailController', DeviceDetailController);

    DeviceDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Device', 'Location'];

    function DeviceDetailController($scope, $rootScope, $stateParams, previousState, entity, Device, Location) {
        var vm = this;

        vm.device = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('masterfaceproApp:deviceUpdate', function(event, result) {
            vm.device = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
