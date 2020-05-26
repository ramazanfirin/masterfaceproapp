(function() {
    'use strict';

    angular
        .module('masterfaceproApp')
        .controller('CompanyController', CompanyController);

    CompanyController.$inject = ['Company'];

    function CompanyController(Company) {

        var vm = this;

        vm.companies = [];

        loadAll();

        function loadAll() {
            Company.query(function(result) {
                vm.companies = result;
                vm.searchQuery = null;
            });
        }
    }
})();
