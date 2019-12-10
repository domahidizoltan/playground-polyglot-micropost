import React from 'react';
import Form from "./Form";

export default class UserForm extends Form {

    formContent(): JSX.Element {
        const data = this.props.formData || {
            nickname: undefined,
            email: undefined,
            firstName: undefined,
            lastName: undefined
        }

        return (
            <div>
                <div className="form-group">
                    <input className="form-control" type="text" 
                        name="nickname" placeholder="nickname" 
                        defaultValue={data.nickname}
                    />
                </div>
                <div className="form-group">
                    <input className="form-control" type="email" 
                        name="email" placeholder="email"
                        defaultValue={data.email}
                    />
                </div>
                <div className="form-group">
                    <input className="form-control" type="text" 
                        name="firstName" placeholder="first name"
                        defaultValue={data.firstName}
                    />
                </div>
                <div className="form-group">
                    <input className="form-control" type="text" 
                        name="lastName" placeholder="last name"
                        defaultValue={data.lastName}
                    />
                </div>
            </div>
        );
    }
}