import React from 'react'
import {BrowserRouter, Switch, Route} from 'react-router-dom'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import '../node_modules/font-awesome/css/font-awesome.min.css'
import './index.css';

import {Header} from './component'
import {Posts, EditPost, Users, EditUser} from './container';

class App extends React.Component {
  render() {
    return (
      <BrowserRouter>
        <div className="container">

          <Header/>
          <Switch>
            <Route exact path="/" component={Posts}/>
            <Route exact path="/posts" component={Posts}/>
            <Route exact path="/posts/:id" component={EditPost}/>
            <Route exact path="/users" component={Users}/>
            <Route exact path="/users/:id" component={EditUser}/>
          </Switch>

        </div>
      </BrowserRouter>
    );
  }
}

export default App;
