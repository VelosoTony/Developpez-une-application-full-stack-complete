import { Topic } from './topic.interface';
import { User } from './user.interface';

export interface Comment {
  id: number;
  topic: Topic;
  user: User;
  content: string;
  createdDate?: Date;
}
