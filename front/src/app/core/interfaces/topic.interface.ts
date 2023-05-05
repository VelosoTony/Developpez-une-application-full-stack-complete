/*
example of topic:
{
  "topic_id": 1,
  "name": "Python",
  "description": "Python est un langage de programmation de haut niveau, interprété et orienté objet. Il a été créé en 1991 par Guido van Rossum et est maintenant l'un des langages de programmation les plus populaires au monde."
}
*/
export interface Topic {
  topic_id: number;
  name: string;
  description: string;
}
