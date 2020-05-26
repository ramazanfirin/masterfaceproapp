(function() {
    'use strict';

    angular
        .module('masterfaceproApp')
        .controller('LocationDetailController', LocationDetailController);

    LocationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Location', 'Floor', 'StaffType'];

    function LocationDetailController($scope, $rootScope, $stateParams, previousState, entity, Location, Floor, StaffType) {
        var vm = this;

        vm.location = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('masterfaceproApp:locationUpdate', function(event, result) {
            vm.location = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
