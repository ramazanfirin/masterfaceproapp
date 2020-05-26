(function() {
    'use strict';

    angular
        .module('masterfaceproApp')
        .controller('PersonDetailController', PersonDetailController);

    PersonDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Person', 'Image', 'StaffType'];

    function PersonDetailController($scope, $rootScope, $stateParams, previousState, entity, Person, Image, StaffType) {
        var vm = this;

        vm.person = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('masterfaceproApp:personUpdate', function(event, result) {
            vm.person = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
