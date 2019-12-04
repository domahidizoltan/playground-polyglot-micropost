import React from 'react';
import './Header.css'

export default class Header extends React.Component {
    render() {
        return (
            <header className="header">
                <div className="row">
                    <div className="col"><h3>Posts</h3></div>
                    <div className="col nav justify-content-end">
                        <div role="group" className="btn-group">
                            <button type="button" className="btn btn-secondary">Posts</button>
                            <button type="button" className="btn btn-dark"><i className="fa fa-plus-square"></i></button>
                        </div>
                        <div role="group" className="btn-group">
                            <button type="button" className="btn btn-secondary">Users</button>
                            <button type="button" className="btn btn-dark"><i className="fa fa-plus-square"></i></button>
                        </div>
                    </div>
                </div>
            </header>
        );
    }
}