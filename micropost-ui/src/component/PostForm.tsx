import React from 'react';
import Form from "./Form";

export default class PostForm extends Form {

    formContent(): JSX.Element {
        return (
            <div>
                <div className="form-group"><input className="form-control" type="text" name="userNickname" placeholder="nickname"/></div>
                <div className="form-group"><textarea className="form-control" name="content" placeholder="content"></textarea></div>
            </div>
        );
    }

}