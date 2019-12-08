import React from 'react';
import Form from "./Form";

export default class UserForm extends Form {

    formContent(): JSX.Element {
        return (
            <div>
                <div className="form-group"><input className="form-control" type="text" name="nickname" placeholder="nickname"/></div>
                <div className="form-group"><input className="form-control" type="email" name="email" placeholder="email"/></div>
                <div className="form-group"><input className="form-control" type="text" name="firstName" placeholder="first name"/></div>
                <div className="form-group"><input className="form-control" type="text" name="lastName" placeholder="last name"/></div>
            </div>
        );
    }
}