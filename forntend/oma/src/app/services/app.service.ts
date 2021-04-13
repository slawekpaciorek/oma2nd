upload(formData) {
  const endpoint = this.service_url+'upload/';
  const httpOptions = headers: new HttpHeaders({    <<<< Changes are here
  'Authorization': 'token xxxxxxx'})
};
return this.http.post(endpoint, formData, httpOptions);
}
