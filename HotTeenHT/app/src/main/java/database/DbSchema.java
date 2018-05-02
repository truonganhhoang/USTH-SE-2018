package database;

public class DbSchema {
    public static final class WorkoutTable {
        public static final String NAME = "Workout";
        public static final class Cols {
            public static final String ID = "_id";
            public static final String NAME = "Name";
        }
    }

    public static final class ExerciseTable {
        public static final String NAME = "Exercise";
        public static final class Cols {
            public static final String ID = "_id";
            public static final String NAME = "Name";
        }
    }

    public static final class RelationshipTable {
        public static final String NAME = "Relationship";
        public static final class Cols {
            public static final String WORKOUTID = "WorkoutId";
            public static final String EXERCISEID = "ExerciseId";
            public static final String REPS = "Reps";
        }
    }
}
