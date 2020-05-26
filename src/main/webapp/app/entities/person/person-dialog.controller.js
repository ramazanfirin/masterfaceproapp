(function() {
    'use strict';

    angular
        .module('masterfaceproApp')
        .controller('PersonDialogController', PersonDialogController);

    PersonDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Person', 'Image', 'StaffType'];

    function PersonDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Person, Image, StaffType) {
        var vm = this;

        vm.person = entity;
        vm.clear = clear;
        vm.save = save;
        vm.images = Image.query();
        vm.stafftypes = StaffType.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.person.id !== null) {
                Person.update(vm.person, onSaveSuccess, onSaveError);
            } else {
                Person.save(vm.person, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('masterfaceproApp:personUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
