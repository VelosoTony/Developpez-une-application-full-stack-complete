import { Topic } from './topic.interface';
import { User } from './user.interface';

export interface Post {
  post_id: number;
  topic: Topic;
  user: User;
  title: string;
  content: string;
  createdDate: Date;
}
