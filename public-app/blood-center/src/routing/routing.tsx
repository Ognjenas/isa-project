import React from 'react';
import {
    BrowserRouter,
    Routes,
    Route,
} from 'react-router-dom';
import routes from './routes';

const Pages = () => {
    return (
        <BrowserRouter>
            <Routes>
                {routes.map(({ path, component: Component }: any, idx: number) => (
                    <Route
                        key={idx}
                        path={path}
                        element={<Component></Component>}
                    />
                ))}
            </Routes>
        </BrowserRouter>
    );
};

export default Pages;



