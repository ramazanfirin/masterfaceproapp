(function() {
    'use strict';

    angular
        .module('masterfaceproApp')
        .controller('ImageDetailController', ImageDetailController);

    ImageDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Image', 'Person'];

    function ImageDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Image, Person) {
        var vm = this;

        vm.image = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('masterfaceproApp:imageUpdate', function(event, result) {
            vm.image = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
