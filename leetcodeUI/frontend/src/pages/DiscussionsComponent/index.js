import React, { useState, useEffect } from 'react';
import DiscussionList from './DiscussionList';
import NewDiscussionForm from './NewDiscussionForm';
import CustomerServices from '../../services/CustomerServices'
const DiscussionsComponent = () => {
  const [discussions, setDiscussions] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Fetch all discussions when the component mounts
    CustomerServices.getAllDiscussions()
      .then((response) => {
        setDiscussions(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error('Error fetching discussions:', error);
        setLoading(false);
      });
  }, []);

  const handleAddDiscussion = (newDiscussion) => {
    const { customerId, title, content } = newDiscussion;
    CustomerServices.createDiscussion(customerId, title, content)
      .then((response) => {
        setDiscussions([response.data, ...discussions]);
      })
      .catch((error) => {
        console.error('Error creating discussion:', error);
      });
  };

  if (loading) {
    return <div>Loading discussions...</div>;
  }

  return (
    <div className="app-container">
      <h1 style={{"color":"whitesmoke", "fontWeight":"bolder"}}>View All Discussions</h1>
      <NewDiscussionForm onAddDiscussion={handleAddDiscussion} />
      <DiscussionList discussions={discussions} />
    </div>
  );
};

export default DiscussionsComponent;
