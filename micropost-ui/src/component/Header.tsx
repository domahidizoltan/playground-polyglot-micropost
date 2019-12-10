import React from 'react';
import {RouteComponentProps, withRouter} from 'react-router'
import './Header.css'
import {AppStateType, stateStore} from '../statestore/AppState'
import { Navigation } from '../common/Helpers';
import { Unsubscribe } from 'redux';

class Header extends React.Component<RouteComponentProps, AppStateType, any> {
    
    storeSubscription?: Unsubscribe
    
    componentWillMount() {
        this.storeSubscription = stateStore.subscribe(this.forceUpdate.bind(this))
    }

    componentWillUnmount() {
        if (this.storeSubscription) {
            this.storeSubscription()
        }
    }

    render() {
        const {headerTitle} = stateStore.getState()
        return (
            <header className="header">
                <div className="row">
                    <div className="col"><h3>{headerTitle}</h3></div>
                    <div className="col-8 nav justify-content-end">
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

    private navTo = (url: string) => Navigation.navTo(url, this.props)
}

export default withRouter(Header)