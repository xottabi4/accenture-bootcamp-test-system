var text = element(by.binding('que1.text'));
var valid = element(by.binding('myForm.input.$valid'));
var input = element(by.model('que1.text'));


it('should initialize to model', function() {
  expect(text.getText()).toContain('que1.text');
  expect(valid.getText()).toContain('true');

});

it('should be invalid if empty', function() {
  input.clear();
  input.sendKeys('');

  expect(text.getText()).toEqual('text =');
  expect(valid.getText()).toContain('false');
});

