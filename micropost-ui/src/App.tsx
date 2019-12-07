import React from 'react'
import {BrowserRouter, Switch, Route, RouteComponentProps} from 'react-router-dom'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import '../node_modules/font-awesome/css/font-awesome.min.css'
import './index.css';

import {Header} from './component'
import {Posts, AddPost, Users, AddUser} from './container';

class App extends React.Component {
  render() {
    return (
      <BrowserRouter>
        <div className="container">

          <Header/>
          <Switch>
            <Route exact path="/" component={Posts}/>
            <Route exact path="/posts" component={Posts}/>
            <Route exact path="/posts/add" component={AddPost}/>
            <Route exact path="/users" component={Users}/>
            <Route exact path="/users/add" component={AddUser}/>
          </Switch>

        </div>
      </BrowserRouter>
    );
  }
}

export default App;
