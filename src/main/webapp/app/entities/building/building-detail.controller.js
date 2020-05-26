(function() {
    'use strict';

    angular
        .module('masterfaceproApp')
        .controller('BuildingDetailController', BuildingDetailController);

    BuildingDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Building', 'Company'];

    function BuildingDetailController($scope, $rootScope, $stateParams, previousState, entity, Building, Company) {
        var vm = this;

        vm.building = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('masterfaceproApp:buildingUpdate', function(event, result) {
            vm.building = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
