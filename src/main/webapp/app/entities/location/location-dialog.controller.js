(function() {
    'use strict';

    angular
        .module('masterfaceproApp')
        .controller('LocationDialogController', LocationDialogController);

    LocationDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Location', 'Floor', 'StaffType'];

    function LocationDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Location, Floor, StaffType) {
        var vm = this;

        vm.location = entity;
        vm.clear = clear;
        vm.save = save;
        vm.floors = Floor.query();
        vm.stafftypes = StaffType.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.location.id !== null) {
                Location.update(vm.location, onSaveSuccess, onSaveError);
            } else {
                Location.save(vm.location, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('masterfaceproApp:locationUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
