import React from 'react';
import Skeleton from 'react-loading-skeleton';

const Loading = ({ count = 4, height = 350 }) => {
    return (
        <>
            {[...Array(count)].map((_, index) => (
                <div className="col-md-3" key={index}>
                    <Skeleton height={height} />
                </div>
            ))}
        </>
    );
};

export default Loading;
