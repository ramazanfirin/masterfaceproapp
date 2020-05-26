'use strict';

describe('Controller Tests', function() {

    describe('Record Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockRecord, MockDevice, MockImage, MockPerson;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockRecord = jasmine.createSpy('MockRecord');
            MockDevice = jasmine.createSpy('MockDevice');
            MockImage = jasmine.createSpy('MockImage');
            MockPerson = jasmine.createSpy('MockPerson');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Record': MockRecord,
                'Device': MockDevice,
                'Image': MockImage,
                'Person': MockPerson
            };
            createController = function() {
                $injector.get('$controller')("RecordDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'masterfaceproApp:recordUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
