import React from 'react';
import {RouteComponentProps, withRouter} from 'react-router'
import './Header.css'
import {AppStateType, stateStore} from '../statestore/AppState'

class Header extends React.Component<RouteComponentProps, AppStateType, any> {
    constructor(props: RouteComponentProps) {
        super(props)
        if (stateStore.getState().headerTitle == '') {
            setTimeout(this.shouldComponentUpdate.bind(this), 200)
        }
    }

    navTo(url: string) {
        const {history} = this.props
        if (history) {
            history.push(url)
        }
    }

    shouldComponentUpdate() {
        this.forceUpdate()
        return true
    }

    render() {
        let {headerTitle} = stateStore.getState()
        return (
            <header className="header">
                <div className="row">
                    <div className="col"><h3>{headerTitle}</h3></div>
                    <div className="col nav justify-content-end">
                        <div role="group" className="btn-group">
                            <button type="button" className="btn btn-secondary" onClick={e => this.navTo("/posts")}>Posts</button>
                            <button type="button" className="btn btn-dark" onClick={e => this.navTo("/posts/add")}>
                                <i className="fa fa-plus-square"></i>
                            </button>
                        </div>
                        <div role="group" className="btn-group">
                            <button type="button" className="btn btn-secondary" onClick={e => this.navTo("/users")}>Users</button>
                            <button type="button" className="btn btn-dark" onClick={e => this.navTo("/users/add")}>
                                <i className="fa fa-plus-square"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </header>
        );
    }
}

export default withRouter(Header)