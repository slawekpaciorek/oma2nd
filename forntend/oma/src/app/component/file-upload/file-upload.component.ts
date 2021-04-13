import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

export class UploadComponent implements OnInit {
  form: FormGroup;
  constructor(private formBuilder: FormBuilder, private uploadService: AppService) {}
  ngOnInit() {
    this.form = this.formBuilder.group({
      profile: ['']
    });
  }

  onChange(event) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];

      this.form.get('profile').setValue(file);
      console.log(this.form.get('profile').value)
    }
  }

  onSubmit() {
    const formData = new FormData();
    formData.append('file', this.form.get('profile').value);

    this.uploadService.upload(formData).subscribe(
      (res) => {
        this.response = res;

        console.log(res);

      },
      (err) => {
        console.log(err);
      });
  }
}
