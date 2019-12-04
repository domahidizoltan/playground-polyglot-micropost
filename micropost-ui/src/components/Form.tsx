import React from 'react';
import './Form.css'

export default class Form extends React.Component {
    render() {
        return (
            <div>
                <div className="contact-clean">
                    <form method="post">
                        <div className="form-group"><input className="form-control" type="text" name="name" placeholder="Name"/></div>
                        <div className="form-group"><input className="form-control is-invalid" type="email" name="email" placeholder="Email"/><small className="form-text text-danger">Please enter a correct email address.</small></div>
                        <div className="form-group"><textarea className="form-control" name="message" placeholder="Message"></textarea></div>
                        <div className="form-group nav justify-content-end">
                            <button className="btn btn-secondary" type="submit">send</button>
                            <button className="btn btn-outline-secondary" type="button">cancel</button>
                        </div>
                    </form>
                    </div>
            </div>
        );
    }
}