import React from 'react';
import Form from "./Form";

export default class PostForm extends Form {

    formContent(): JSX.Element {
        const data = this.props.formData || {
            userNickname: undefined,
            content: undefined
        }

        return (
            <div>
                <div className="form-group">
                    <input className="form-control" type="text" 
                        name="userNickname" placeholder="nickname"
                        defaultValue={data.userNickname}
                    />
                </div>
                <div className="form-group">
                    <textarea className="form-control" 
                        name="content" placeholder="content"
                        defaultValue={data.content}/>
                </div>
            </div>
        );
    }

}